
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

    }
});

$('#btn').on('click', function(){
    window.location.href = "AuthorController?action=loadTable";
});

function getAllAuthors(author) {
    var index = 0;
    var row;
    
    $.each(author, function (author) {
        console.log(this);
        index++;
        row = "<tr id='row" + index + "' " + "class='authorTableDataRow' >"+
            "<td>"+ this.authorId+ "</td>"+
            "<td>"+ this.firstName+ "</td>"+
            "<td>"+ this.lastName+ "</td>"+
            //"<td>",authors.size,"</td>",
            "</tr>";
            $('#authorTableBody').append(row);
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

$("#bookTableData tr").on('click', function () {
    var formObjects = [$('#bookID'), $('#title'), $('#datePublished'), $("#authorID")];
    var table = document.getElementById("bookTableData");
    var row = table.rows[this.rowIndex];
    for (var i = 0; i < formObjects.length; i++) {
        formObjects[i].val(row.cells[i].textContent);
    }
});




//    });
//});

/*
 $('#clearButton').on('click', function(){
 document.getElementById("add_editForm").reset();
 }); */