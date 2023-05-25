let pagingSection = document.querySelector('.gallery-paging-section');
let pageButtons = document.getElementsByClassName('paging-button');
let buttonCount = pageButtons.length;
let width = 20 + (buttonCount * 40);
pagingSection.style.width = width +'px';