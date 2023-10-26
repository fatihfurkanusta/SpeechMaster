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

function speechToText(){
    
    recognition.addEventListener('result', (e) =>{
        
        createdText = Array.from(e.results)
            .map(result => result[0])
            .map(result => result.transcript)
            .join('');

        
        p.innerText = createdText;
        text.appendChild(p);

        console.log(e);
    });
}

function sendMessage(){
    let li = document.createElement('li');
    let sendP = document.createElement('p');
    sendP.innerText = createdText;
    
    sendP.style.float = "right";
    sendP.style.background = "lightgrey";

    li.appendChild(sendP);
    listText.appendChild(li);
    data = createdText;

    p.innerText = '';
    text.appendChild(p);
}

function postData(){
    
    var url = "http://localhost:8080/userMessage/post";

    var xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-type', 
    'application/json; charset=utf-8');
    xhr.onloadend = function () {
        console.log(xhr.status);
        console.log(xhr.readyState);
    }
    xhr.send(data);
    console.log(xhr.responseText);
}

async function transmitChatGPTMessage(){
    var url = "http://localhost:8080/userMessage/get";
    let a = '';
    const response = fetch(url);
    response.then((response) => {
        response.text().then((text) => {
            a = text;
            showResponse(text);
            console.log("sdsa"+a);
        });
    });

    console.log("sdssdsda"+a);
}

function showResponse(chatGPTResponse){
    let li = document.createElement('li');
    let sendP = document.createElement('p');
    sendP.innerText = chatGPTResponse;

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
    transmitChatGPTMessage();
});
