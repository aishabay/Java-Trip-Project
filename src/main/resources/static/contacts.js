const toastLiveExample = document.getElementById('liveToast')
submit_request.addEventListener('click',addRequest);

function addRequest(){
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/request", true);
    xhr.setRequestHeader("Content-type", "application/json");
    const params = {
        name : request_name.value,
        email : request_email.value,
        number : request_number.value,
        message : request_message.value
    }

    xhr.send(JSON.stringify(params));

    request_name.value = "";
    request_email.value = "";
    request_number.value = "";
    request_message.value = "";

    const toast = new bootstrap.Toast(toastLiveExample)
    toast.show()
}