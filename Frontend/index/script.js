const inputDiv = document.querySelector('.text-input');
const text = document.querySelector('.send-text');
const listText = document.querySelector('.list-text');
const chatboxTop = document.querySelector('.chatbox-top');

window.SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;

const recognition = new window.SpeechRecognition();

recognition.interimResults = true;
recognition.continuous = true;
recognition.lang = 'en-US';

let p = document.createElement('p');
var createdText = '';
let data = '';
let chatGPTResponse = '';

function speechToText(){
    
    recognition.addEventListener('result', (e) =>{
        
        createdText = Array.from(e.results)
            .map(result => result[0])
            .map(result => result.transcript)
            .join('');

        text.value = createdText;

        console.log(e);
    });
}

function sendMessage(){
    let li = document.createElement('li');
    let sendP = document.createElement('p');
    sendP.innerText = text.value;
    
    sendP.style.float = "right";
    sendP.style.background = "lightgrey";

    li.appendChild(sendP);
    listText.appendChild(li);
    data = text.value;

    text.value = '';
}

function postData(){
    var url = "http://localhost:8081/api/searchChatGPT";

    const xhr = new XMLHttpRequest();
    xhr.open('POST', url);
    xhr.setRequestHeader('Content-type', 
    'application/json');

    /* xhr.onloadend = function () {
        console.log(xhr.status);
        console.log(xhr.readyState);
    }; */
    
    const dataJson = `{
        "query": "${data}"
    }`;


    xhr.send(dataJson);
    console.log(xhr.responseText);


    xhr.onload = () => {
        if (xhr.status === 200) {
          chatGPTResponse = xhr.responseText;
        } else {
          console.log(xhr.status);
        }
    };
}

function showResponse(){
    let li = document.createElement('li');
    let sendP = document.createElement('p');

    if(chatGPTResponse != null){
        sendP.innerText = chatGPTResponse;
    }else{
        sendP.innerText = 'Please try again.'
    }

    li.appendChild(sendP);
    listText.appendChild(li);
}


let clickCount = 0;

const speakButton = document.querySelector(".speak-button");
const sendButton = document.querySelector('.send-button');

speakButton.addEventListener("click", () => {
  clickCount++;

  if (clickCount % 2 === 1) {
    speakButton.innerHTML = "<i class='fa-solid fa-microphone-slash'></i>";
    sendButton.disabled = true;
    recognition.start();
    speechToText();
  } else {
    speakButton.innerHTML = "<i class='fa-solid fa-microphone'></i>";
    sendButton.disabled = false;
    recognition.stop();
  }
});


sendButton.addEventListener('click', ()=>{
    sendMessage();
    recognition.stop();
    postData();
    setTimeout(() => {
        showResponse();
    }, 6000);
});

text.addEventListener('')
