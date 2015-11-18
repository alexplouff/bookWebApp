
var authorTableBody = document.getElementById('authorTableData');
var authorUrl = "AuthorController";
var $body = $('body');
$(document).ready(function () {

    if ($body.attr('class') === "splash") {

        $.ajax({
            type: 'GET',
            url: "AuthorController?action=listAjax",
            success: function (authors) {
                getAllAuthors(authors);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Could not get authors for this user due to: " + errorThrown.toString());
            }
        });

    }
});

function getAllAuthors(authors) {
    var index = 0;
    var tableParts;
    $.each(authors, function () {
        index++;
        tableParts = ["<tr id='row" + index.toString() + "'" + "class='authorTableDataRow' >",
            "<td>", authors.authorId, "</td>",
            "<td>", authors.firstName, "</td>",
            "<td>", authors.lastName, "</td>",
            //"<td>",authors.size,"</td>",
            "</tr>"];
    });
    authorTableBody.append(tableParts.join());
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