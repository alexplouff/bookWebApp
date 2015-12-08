
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
            success: function (books) {
                getAllBooks(books);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert("Could not get authors for this user due to: " + errorThrown.toString());

            }

        });

    }
});

function saveBook(){
    var $serializedForm = $('#bookForm').serialize();
    $.ajax({
        type: 'POST',
        url: 'BookController?action=save',
        data: $serializedForm,
        
        success: function(books){
            deleteTableRows();
            getAllBooks(books);
            
        }
    });
}

function saveAuthor(){
    var $serializedForm = $('#bookForm').serialize();
    $.ajax({
        type: 'POST',
        url: 'AuthorController?action=save',
        data : $serializedForm,
        
        success: function(authors){
            deleteAuthorTableRows();
            getAllAuthors(authors);
        }
    });
}

function deleteBooks(){
    var $serializedTblForm = $('#bookTableForm').serialize();
    $.ajax({
        type: 'POST',
        url: 'BookController?action=delete',
        data: $serializedTblForm,
        dataType: 'json',
        success: function(books){
            deleteTableRows();
            getAllBooks(books);
        }
    });
       
}

function deleteAuthors(){
    var $serializedTblForm = $('#authorTableForm').serialize();
        $.ajax({
        type: 'POST',
        url: 'AuthorController?action=delete',
        data: $serializedTblForm,
        dataType: 'json',
        success: function(authors){
            deleteAuthorTableRows();
            getAllAuthors(authors);
        }
    });
}

function deleteTableRows(){
    $('#bookTable tr').remove();
}

function deleteAuthorTableRows(){
    $('#authorTable tr').remove();
}

function formToJSON() {
	return JSON.stringify({
		"bookID": $('#bookID').val(),
		"title": $('#title').val(),
		"datePublished": $('#datePublished').val(), 
		"authorID": $('#authorID').val()
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
                "<td> <input type='checkbox' class='authorDeleteBoxes' name='authorDeleteBoxes' value='"+this.authorId+"' />  </td>" +
                "</tr>";
        $('#authorTableBody').append(row);
    });
}

function getAllBooks(books) {
    var row;
    var index = 0;
    
    $.each(books, function () {
        index++;
        row = "<tr id='bookRow" + index + "' " + "class='bookTableDataRow'>" +
                "<td>" + this.bookId + "</td>" +
                "<td>" + this.title + "</td>" +
                "<td>" + this.datePublished + "</td>" +
                "<td>" + this.authorId + "</td>" +
                "<td> <input type='checkbox' class='deleteBoxes' name='deleteBoxes' value='"+this.bookId+"' />  </td>" +
                "</tr>";
        $('#bookTableBody').append(row);
    });
}

var $bookTbody = $('#bookTableBody');

$($bookTbody).on('click', 'tr', function () {
    var formObjects = [$('#authorId'), $('#firstName'), $('#lastName')];
    for (var i = 0; i < formObjects.length; i++) {
        formObjects[i].val(this.cells[i].textContent);
    }
});

var $authorTbody = $('#authorTableBody');

$($authorTbody).on('click', 'tr', function () {
    var formObjects = [$('#authorId'), $('#firstName'), $('#lastName')];
    for (var i = 0; i < formObjects.length; i++) {
        formObjects[i].val(this.cells[i].textContent);
    }
});
 
$('#bookSubmitBtn').on('click', function(){
   saveBook();
});

$('#bookDeleteBtn').on('click', function(){
    deleteBooks();
    
});

$('#authorSubmitBtn').on('click', function(){
   saveAuthor();
});

$('#authorDeleteBtn').on('click', function(){
    deleteAuthors();
    
});


function getCheckBoxValues(){
    var x = document.getElementsByClassName('deleteBoxes');
    var values = [];
    for(var i = 0; i < x.length; i++){
        if(x[i].checked){
           values.push(x[i].value);
        }
    }
    return values;
    
}



//    });
//});

/*
 $('#clearButton').on('click', function(){
 document.getElementById("add_editForm").reset();
 }); */