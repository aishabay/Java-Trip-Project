let id = document.getElementById("tour_id").value;

var tour = new XMLHttpRequest();
tour.open('GET', '/tours/'+id, true)
// tours.responseType = 'json';
tour.onload = function() {
    if (tour.status >= 200 && tour.status < 400) {
        var data = JSON.parse(tour.responseText);
        tour_name.innerHTML = data.name;
        tour_name_reserve.innerHTML = data.name;
        tour_id_reserve.innerHTML = data.id;
        tour_type_number_people.innerHTML = data.typeNumberPeople;
        tour_type_duration.innerHTML = data.typeDuration;
        tour_duration.innerHTML = data.duration;
        tour_days_hours.innerHTML = data.daysHours;
        tour_picture.backgroundImage = "url('"+data.picture+"')";
        console.log(data);
        console.log(data.picture);
        // insertTourName(data);
        // insertTourTypeNumberPeople(data);
        // insertTourTypeDuration(data);
        // insertTourDuration(data);
        // insertTourDaysHours(data);
        setTourPicture(data);
        insertTourDescription(data);
    } else {
        console.log("We connected to the server, but it returned an error");
    }
};
tour.onerror = function () {
    console.log("Connection error");
}
tour.send();



var tour_desc = new XMLHttpRequest();
tour_desc.open('GET', '/desc/'+id, true)
tour_desc.onload = function () {
    if (tour_desc.status >= 200 && tour_desc.status < 400) {
        var data = JSON.parse(tour_desc.responseText);
        insertTourDescription(data);
    } else {
        console.log("We connected to the server, but it returned an error");
    }
}
tour_desc.onerror = function () {
    console.log("Connection error");
}
tour_desc.send();



var tour_places_short = new XMLHttpRequest();
tour_places_short.open('GET', '/place/short/'+id, true)
tour_places_short.onload = function () {
    if (tour_places_short.status >= 200 && tour_places_short.status < 400) {
        var data = JSON.parse(tour_places_short.responseText);
        insertTourPlacesShort(data);
    } else {
        console.log("We connected to the server, but it returned an error");
    }
}
tour_places_short.onerror = function () {
    console.log("Connection error");
}
tour_places_short.send();



var images = [];
var tour_places_long = new XMLHttpRequest();
tour_places_long.open('GET', '/place/long/'+id, true)
tour_places_long.onload = function () {
    if (tour_places_long.status >= 200 && tour_places_long.status < 400) {
        var data = JSON.parse(tour_places_long.responseText);
        insertTourPlacesLong(data);
        document.getElementById("pic").src = data[0].image;
        // insertTourSlider(data);
        for(let i=0;i<data.length;i++){
            images[i] = data[i].image;
        }
    } else {
        console.log("We connected to the server, but it returned an error");
    }
}
tour_places_long.onerror = function () {
    console.log("Connection error");
}
tour_places_long.send();



// Changing images in the Slider of Tour Page
var count = 0;
btn1.addEventListener('click', changePicPrev);
btn2.addEventListener('click', changePicNext);

function changePicNext() {
    if(count<images.length-1){
        count++;
        pic.src = images[count];
    }else{
        count = 0;
        pic.src = images[count];
    }
}
function changePicPrev(){
    if(count>0){
        count--;
        pic.src = images[count];
    }else{
        count = images.length - 1;
        pic.src = images[count];
    }
}



var tour_prices = new XMLHttpRequest();
tour_prices.open('GET', '/price/'+id, true)
tour_prices.onload = function () {
    if (tour_prices.status >= 200 && tour_prices.status < 400) {
        var data = JSON.parse(tour_prices.responseText);
        insertTourPrices(data);
    } else {
        console.log("We connected to the server, but it returned an error");
    }
}
tour_prices.onerror = function () {
    console.log("Connection error");
}
tour_prices.send();



if (isLoggedIn.value != "anonymousUser"){
    tour_count_likes.addEventListener('click', like);
    tour_count_dislikes.addEventListener('click', dislike);
    submit_comment.addEventListener('click', addComment);
    window.addEventListener("load", changeLikeInitialColor)
    window.addEventListener("load", changeDislikeInitialColor)
}
window.addEventListener("load", getLikeNumber);
window.addEventListener("load", getDislikeNumber);
window.addEventListener("load", loadComments);
window.addEventListener("load", getCommentNumber);



// Definitions of all functions for Handlebar

function insertTourName(data) {
    var rawTemplate = document.getElementById("tourNameTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var element = document.getElementById("tour_name");
    element.innerHTML = generatedHTML;
}
function insertTourTypeNumberPeople(data){
    var rawTemplate = document.getElementById("tourTypeNumberPeopleTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var element = document.getElementById("tour_type_number_people");
    element.innerHTML = generatedHTML;
}
function insertTourTypeDuration(data){
    var rawTemplate = document.getElementById("tourTypeDurationTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var element = document.getElementById("tour_type_duration");
    element.innerHTML = generatedHTML;
}
function insertTourDuration(data){
    var rawTemplate = document.getElementById("tourDurationTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var element = document.getElementById("tour_duration");
    element.innerHTML = generatedHTML;
}
function insertTourDaysHours(data){
    var rawTemplate = document.getElementById("tourDaysHoursTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var element = document.getElementById("tour_days_hours");
    element.innerHTML = generatedHTML;
}
function setTourPicture(data){
    var rawTemplate = document.getElementById("tourPictureTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var element = document.getElementById("tour_picture");
    // element.setAttribute("style","background-image: url("+generatedHTML+");");
    element.style.backgroundImage = "url("+generatedHTML+")";
}
function insertTourDescription(data){
    var rawTemplate = document.getElementById("tourDescriptionTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var element = document.getElementById("tour_description");
    element.innerHTML = generatedHTML;
}
function insertTourPlacesShort(data){
    var rawTemplate = document.getElementById("tourPlacesShortTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var element = document.getElementById("tour_places_short");
    element.innerHTML = generatedHTML;
}
function insertTourPlacesLong(data){
    var rawTemplate = document.getElementById("tourPlacesLongTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var element = document.getElementById("tour_places_long");
    element.innerHTML = generatedHTML;
}
// function insertTourSlider(data){
//     var rawTemplate = document.getElementById("tourSliderTemplate").innerHTML;
//     var compiledTemplate = Handlebars.compile(rawTemplate);
//     var generatedHTML = compiledTemplate(data);
//
//     var element = document.getElementById("tour_slider");
//     element.innerHTML = generatedHTML;
// }
function insertTourPrices(data){
    var rawTemplate = document.getElementById("tourPricesTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var element = document.getElementById("tour_prices");
    element.innerHTML = generatedHTML;
}
function getLikeNumber(){
    var tour_likes = new XMLHttpRequest();
    tour_likes.open('GET', '/like/'+id, true)
    tour_likes.setRequestHeader("Content-Type", "application/json");
    tour_likes.onload = function () {
        if (tour_likes.status >= 200 && tour_likes.status < 400) {
            var data = JSON.parse(tour_likes.responseText);
            if(data!=0){
                tour_count_likes.innerHTML = data;
            }else{
                tour_count_likes.innerHTML = "";
            }
            // console.log(isLoggedIn.value)
        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    tour_likes.onerror = function () {
        console.log("Connection error");
    }
    tour_likes.send();
}
function getDislikeNumber(){
    var tour_dislikes = new XMLHttpRequest();
    tour_dislikes.open('GET', '/dislike/'+id, true)
    tour_dislikes.setRequestHeader("Content-Type", "application/json");
    tour_dislikes.onload = function () {
        if (tour_dislikes.status >= 200 && tour_dislikes.status < 400) {
            var data = JSON.parse(tour_dislikes.responseText);
            if(data!=0){
                tour_count_dislikes.innerHTML = data;
            }else{
                tour_count_dislikes.innerHTML = "";
            }

            // console.log(isLoggedIn.value)
        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    tour_dislikes.onerror = function () {
        console.log("Connection error");
    }
    tour_dislikes.send();
}
function changeLikeInitialColor(){
    var tour_like_initial = new XMLHttpRequest();
    tour_like_initial.open('GET', '/like/check/initial/'+id, true)
    tour_like_initial.setRequestHeader("Content-Type", "application/json");
    tour_like_initial.onload = function () {
        if (tour_like_initial.status >= 200 && tour_like_initial.status < 400) {
            var data = JSON.parse(tour_like_initial.responseText);
            console.log(data);
            if(!data){
                tour_count_likes.classList.remove("text-secondary","border-secondary");
                tour_count_likes.classList.add("text-primary","border-primary");
            }else{
                tour_count_likes.classList.remove("text-primary","border-primary");
                tour_count_likes.classList.add("text-secondary","border-secondary");
            }
        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    tour_like_initial.onerror = function () {
        console.log("Connection error");
    }
    tour_like_initial.send();
}
function changeDislikeInitialColor(){
    var tour_dislike_initial = new XMLHttpRequest();
    tour_dislike_initial.open('GET', '/dislike/check/initial/'+id, true)
    tour_dislike_initial.setRequestHeader("Content-Type", "application/json");
    tour_dislike_initial.onload = function () {
        if (tour_dislike_initial.status >= 200 && tour_dislike_initial.status < 400) {
            var data = JSON.parse(tour_dislike_initial.responseText);
            console.log(data);
            if(!data){
                tour_count_dislikes.classList.remove("text-secondary","border-secondary");
                tour_count_dislikes.classList.add("text-primary","border-primary");
            }else{
                tour_count_dislikes.classList.remove("text-primary","border-primary");
                tour_count_dislikes.classList.add("text-secondary","border-secondary");
            }
        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    tour_dislike_initial.onerror = function () {
        console.log("Connection error");
    }
    tour_dislike_initial.send();
}
function changeLikeColor(){
    var tour_like_present = new XMLHttpRequest();
    tour_like_present.open('GET', '/like/check/'+id, true)
    tour_like_present.setRequestHeader("Content-Type", "application/json");
    tour_like_present.onload = function () {
        if (tour_like_present.status >= 200 && tour_like_present.status < 400) {
            var data = JSON.parse(tour_like_present.responseText);
            console.log(data);
            if(data==true){
                tour_count_likes.classList.remove("text-secondary","border-secondary");
                tour_count_likes.classList.add("text-primary","border-primary");
            }else{
                tour_count_likes.classList.remove("text-primary","border-primary");
                tour_count_likes.classList.add("text-secondary","border-secondary");
            }
        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    tour_like_present.onerror = function () {
        console.log("Connection error");
    }
    tour_like_present.send();
}
function changeDislikeColor(){
    var tour_dislike_present = new XMLHttpRequest();
    tour_dislike_present.open('GET', '/dislike/check/'+id, true)
    tour_dislike_present.setRequestHeader("Content-Type", "application/json");
    tour_dislike_present.onload = function () {
        if (tour_dislike_present.status >= 200 && tour_dislike_present.status < 400) {
            var data = JSON.parse(tour_dislike_present.responseText);
            console.log(data);
            if(data==true){
                tour_count_dislikes.classList.remove("text-secondary","border-secondary");
                tour_count_dislikes.classList.add("text-primary","border-primary");
            }else{
                tour_count_dislikes.classList.remove("text-primary","border-primary");
                tour_count_dislikes.classList.add("text-secondary","border-secondary");
            }
        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    tour_dislike_present.onerror = function () {
        console.log("Connection error");
    }
    tour_dislike_present.send();
}
function changeLikeNumber(){
    changeLikeColor();
    var tour_likes = new XMLHttpRequest();
    tour_likes.open('PUT', '/like/'+id, true)
    tour_likes.setRequestHeader("Content-Type", "application/json");
    tour_likes.onload = function () {
        if (tour_likes.status >= 200 && tour_likes.status < 400) {
            var data = JSON.parse(tour_likes.responseText);
            if(data!=0){
                tour_count_likes.innerHTML = data;
            }else{
                tour_count_likes.innerHTML = "";
            }

        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    tour_likes.onerror = function () {
        console.log("Connection error");
    }
    tour_likes.send();
}
function changeDislikeNumber(){
    changeDislikeColor();
    var tour_dislikes = new XMLHttpRequest();
    tour_dislikes.open('PUT', '/dislike/'+id, true)
    tour_dislikes.setRequestHeader("Content-Type", "application/json");
    tour_dislikes.onload = function () {
        if (tour_dislikes.status >= 200 && tour_dislikes.status < 400) {
            var data = JSON.parse(tour_dislikes.responseText);
            if(data!=0){
                tour_count_dislikes.innerHTML = data;
            }else{
                tour_count_dislikes.innerHTML = "";
            }

        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    tour_dislikes.onerror = function () {
        console.log("Connection error");
    }
    tour_dislikes.send();
}
function like(){
    changeLikeNumber();
    // changeDislikeNumber();
    getDislikeNumber();
    changeDislikeInitialColor();
}
function dislike(){
    changeDislikeNumber();
    // changeLikeNumber();
    getLikeNumber();
    changeLikeInitialColor();
}
function getCommentNumber(){
    var tour_com_num = new XMLHttpRequest();
    tour_com_num.open('GET', '/comment/comment-number/'+id, true)
    tour_com_num.setRequestHeader("Content-Type", "application/json");
    tour_com_num.onload = function () {
        if (tour_com_num.status >= 200 && tour_com_num.status < 400) {
            var data = JSON.parse(tour_com_num.responseText);
            tour_count_comments.innerHTML = data;
            // console.log(isLoggedIn.value)
        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    tour_com_num.onerror = function () {
        console.log("Connection error");
    }
    tour_com_num.send();
}
function addComment(){
    let tour_add_com = new XMLHttpRequest();
    tour_add_com.open('POST','/comment/'+id,true)
    tour_add_com.setRequestHeader("Content-Type","application/json");
    const params = {
        comment: tour_comment.value
    };
    tour_add_com.send(JSON.stringify(params));
    tour_add_com.onload = () => {
        loadComments();
        getCommentNumber();
        tour_comment.value = "";
    }
}
function loadComments(){
    var tour_com = new XMLHttpRequest();
    tour_com.open('GET','/comment/'+id,true)
    tour_com.onload = () => {
        if (tour_com.status >= 200 && tour_com.status < 400) {
            var data = JSON.parse(tour_com.responseText);
            insertTourComments(data);
            // console.log(data);
        } else {
            console.log("We connected to the server, but it returned an error");
        }
    }
    tour_com.onerror = function () {
        console.log("Connection error");
    }
    tour_com.send();
}
function insertTourComments(data){
    var rawTemplate = document.getElementById("tourCommentsTemplate").innerHTML;
    var compiledTemplate = Handlebars.compile(rawTemplate);
    var generatedHTML = compiledTemplate(data);

    var element = document.getElementById("tour_comments");
    element.innerHTML = generatedHTML;
}