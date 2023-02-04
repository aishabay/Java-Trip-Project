var tours = new XMLHttpRequest();
tours.open('GET', '/tours', true)
// tours.responseType = 'json';
tours.onload = function() {
    if (tours.status >= 200 && tours.status < 400) {
        var data = JSON.parse(tours.responseText);
        createToursList(data);
    } else {
        console.log("We connected to the server, but it returned an error");
    }
};

tours.onerror = function () {
    console.log("Connection error");
}

tours.send();

function createToursList(data) {
    var rawTemplate = document.getElementById("toursTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var toursContainer = document.getElementById("tours-container");
    toursContainer.innerHTML = generatedHTML;
}