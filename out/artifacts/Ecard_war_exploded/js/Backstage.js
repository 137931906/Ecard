function showHidden(node) {
    $(node).next().slideToggle("slow");
    console.log(node.nextElementSibling);
}

function changePath(node) {
    console.log(node)
    console.log(node.title)
    //刷新
    $('#iframe').attr('src',node.title);

}