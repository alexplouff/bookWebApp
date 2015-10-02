$(document).ready(function(){
   document.getElementById("add_editForm").reset();
});

$('#recordTable tr').on('click',function(){
    
    var formObjects= [$('#bookID'), $('#title'), $('#datePublished'), $('#authorFirstName'), $('#authorLastName'), $('#authorID')];
    var table = document.getElementById("recordTable");
    var row = table.rows[this.rowIndex];
    for(var i=0; i<formObjects.length; i++){
            formObjects[i].val(row.cells[i].textContent);
    }
    
});

$('#clearButton').on('click', function(){
    document.getElementById("add_editForm").reset();
    });