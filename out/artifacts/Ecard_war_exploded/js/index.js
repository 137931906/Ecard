function showOrHide(node) {
    console.log(node.nextElementSibling);
    if (node.nextElementSibling.style.display == "none") {
        node.nextElementSibling.style.display = "block";
    } else {
        node.nextElementSibling.style.display = "none";
    }

    function changePath() {
        document.getElementById("iframe").src = node.title;
    }
}