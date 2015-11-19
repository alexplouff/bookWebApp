
var authorTableBody = document.getElementById('authorTableData');
var authorUrl = "AuthorController";
var $body = $('body');
$(document).ready(function () {

    if ($body.attr('class') === "splash") {

        $.ajax({
            type: "GET",
            url: "AuthorController?action=loadTable",
            success: function (author) {
                getAllAuthors(author);

            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert("Could not get authors for this user due to: " + errorThrown.toString());

            }

        });

        $.ajax({
            type: "GET",
            url: "BookController?action=loadTable",
            success: function ( book) {
                getAllBooks(book);

            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert("Could not get authors for this user due to: " + errorThrown.toString());

            }

        });

    }
});

function addBook(){
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: 'BookController?action=save',
        dataType: 'json',
        data: formToJSON(),
        success: function(author){
            getAllAuthors(author);
        }
    });
            
}

function getAllAuthors(author) {
    var index = 0;
    var row;

    $.each(author, function () {
        index++;
        row = "<tr id='row" + index + "' " + "class='authorTableDataRow' >" +
                "<td>" + this.authorId + "</td>" +
                "<td>" + this.firstName + "</td>" +
                "<td>" + this.lastName + "</td>" +
                "</tr>";
        $('#authorTableBody').append(row);
    });
}

function getAllBooks(book) {
    var row;
    var index = 0;
    $.each(book, function () {
        index++;
        row = "<tr id='bookRow" + index + "' " + "class='bookTableDataRow'>" +
                "<td>" + this.bookId + "</td>" +
                "<td>" + this.title + "</td>" +
                "<td>" + this.datePublished + "</td>" +
                "<td>" + this.authorId + "</td>" +
                "</tr>";
        $('#bookTableBody').append(row);
    });
}

$('#tableData tr').on('click', function () {

    var formObjects = [$('#authorID'), $('#firstName'), $('#lastName')];
    var table = document.getElementById("tableData");
    var row = table.rows[this.rowIndex];
    for (var i = 0; i < formObjects.length; i++) {
        formObjects[i].val(row.cells[i].textContent);
    }

});

var $tbody = $('#bookTableBody');

$($tbody).on('click', 'tr', function () {
    var formObjects = [$('#bookID'), $('#title'), $('#datePublished'), $("#authorID")];
    for (var i = 0; i < formObjects.length; i++) {
        formObjects[i].val(this.cells[i].textContent);
    }
});

$('#bookSubmitBtn').on('click', function(){
   addBook();
});




//    });
//});

/*
 $('#clearButton').on('click', function(){
 document.getElementById("add_editForm").reset();
 }); */