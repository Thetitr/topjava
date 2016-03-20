/**
 * Created by Thetitr on 19.03.2016.
 */
function makeEditable() {

    $('#add').click(function () {
        /*$('#id').val(0);*/
        $('#editMeal').modal();
    });

    $('.delete').click(function () {
        deleteRow($(this).attr("id"));
    });

    $('#mealDetailsForm').submit(function () {
        save();
        return false;
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxMealUrl,
        type: 'DELETE',
        data:{"id":id},
        success: function () {
            updateTable();
        }
    });
}

function updateTable() {
    $.get(ajaxMealUrl, function (data) {
        oTable_datatable.fnClearTable();
        $.each(data, function (key, item) {
            oTable_datatable.fnAddData(item);
        });
        oTable_datatable.fnDraw();
    });
}

function save() {
    var form = $('#mealDetailsForm');
    $.ajax({
        type: "POST",
        url: ajaxMealUrl+"create",
        data: form.serialize(),
        success: function () {
            $('#editMeal').modal('hide');
            updateTable();
        }
    });
}