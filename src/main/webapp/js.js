$(document).ready(function () {
//   document.getElementById("add_editForm").reset();

});

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

/*
 $('#clearButton').on('click', function(){
 document.getElementById("add_editForm").reset();
 }); */