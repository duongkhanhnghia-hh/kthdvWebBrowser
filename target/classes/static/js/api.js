
function rentBook(bookCode) {
    console.log("hel");
    $.post('/user/rentbooks',{"bookCode":bookCode}).done(function (html) {
        if (html !== "fail"){
            top.location.href = html;
        }

    });
}