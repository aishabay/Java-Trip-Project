var toursHome = new XMLHttpRequest();
toursHome.open('GET', '/tours/home', true)
// tours.responseType = 'json';
toursHome.onload = function() {
    if (toursHome.status >= 200 && toursHome.status < 400) {
        let data = JSON.parse(toursHome.responseText);
        createToursListHome(data);
    } else {
        console.log("We connected to the server, but it returned an error");
    }
};

toursHome.onerror = function () {
    console.log("Connection error");
}

toursHome.send();

function createToursListHome(data) {
    var rawTemplate = document.getElementById("toursHomeTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var toursContainer = document.getElementById("tours-home-container");
    toursContainer.innerHTML = generatedHTML;
}


var excursionsHome = new XMLHttpRequest();
excursionsHome.open('GET', '/excursions/home', true)
// tours.responseType = 'json';
excursionsHome.onload = function() {
    if (excursionsHome.status >= 200 && excursionsHome.status < 400) {
        let data = JSON.parse(excursionsHome.responseText);
        createExcursionsListHome(data);
    } else {
        console.log("We connected to the server, but it returned an error");
    }
};

excursionsHome.onerror = function () {
    console.log("Connection error");
}

excursionsHome.send();

function createExcursionsListHome(data) {
    var rawTemplate = document.getElementById("excursionsHomeTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var excursionsContainer = document.getElementById("excursions-home-container");
    excursionsContainer.innerHTML = generatedHTML;
}