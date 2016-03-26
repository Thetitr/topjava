/**
 * Created by Thetitr on 26.03.2016.
 */
var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: function (data) {
            updateTableByData(data);
        }
    });
    return false;
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "sAjaxSource": ajaxUrl,
        "sAjaxDataProp": "",
        "bPaginate": false,
        "bInfo": false,
        "aoColumns": [

            {
                "mData": "dateTime"
            },
            {
                "mData": "description"
            },
            {
                "mData": "calories"
            },
            {
                "mData":   "exceed",
                "bVisible": false
            },
            {
                "sDefaultContent": "Edit",
                "bSortable": false,
                "mRender": renderEditBtn
            },
            {
                "sDefaultContent": "Delete",
                "bSortable": false,
                "mRender": renderDeleteBtn
            }
        ],
        "aaSorting": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.exceed) {
                $(row).css("color", "green");
            }
            else
                $(row).css("color", "red");
        },


        "initComplete": makeEditable
    });

    $('#filter').submit(function () {
        updateTable();
        return false;
    });

    init();
});



function init() {
}