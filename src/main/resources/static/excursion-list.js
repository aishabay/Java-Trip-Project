var excursions = new XMLHttpRequest();
excursions.open('GET', '/excursions', true)
// tours.responseType = 'json';
excursions.onload = function() {
    if (excursions.status >= 200 && excursions.status < 400) {
        var data = JSON.parse(excursions.responseText);
        createExcursionsList(data);
    } else {
        console.log("We connected to the server, but it returned an error");
    }
};

excursions.onerror = function () {
    console.log("Connection error");
}

excursions.send();

function createExcursionsList(data) {
    var rawTemplate = document.getElementById("excursionsTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var excursionsContainer = document.getElementById("excursions-container");
    excursionsContainer.innerHTML = generatedHTML;

}