const navBg = document.querySelector('#navbar-bg')
const navTogg = document.querySelector('.navbar-toggler')
navTogg.addEventListener('click', function() {
    var isExpanded = document.querySelector('button[aria-expanded]')?.getAttribute('aria-expanded')
    if (isExpanded=='true'){
        navBg.style.backgroundColor = `rgb(43, 43, 48)`
    }else if(document.body.scrollTop > 20 || document.documentElement.scrollTop > 20){
        navBg.style.backgroundColor = `rgb(43, 43, 48)`
    }
    else{
        navBg.style.backgroundColor = `#ffffff00`
    }
});

// When the user scrolls down 50px from the top of the document, resize the header's font size
window.onscroll = function() {scrollFunction()};
function scrollFunction() {
    var isExpanded = document.querySelector('button[aria-expanded]')?.getAttribute('aria-expanded')
    if(document.body.scrollTop > 20 || document.documentElement.scrollTop > 20){
        navBg.style.backgroundColor = `rgb(43, 43, 48)` //rgb(251, 251, 251)
    } else if(isExpanded=='true'){
        navBg.style.backgroundColor = `rgb(43, 43, 48)`
    } else {
        navBg.style.backgroundColor = `#ffffff00`
    }
}

// document.getElementById("myForm").addEventListener("click", function (event){
//     event.preventDefault()
// });