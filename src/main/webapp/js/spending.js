$(document).ready(function () {

    $("#historyTableSpend").hide(); // start without table
    $("#popupSpend").hide();
    function PopUpShowSpend() {
        $("#popupSpend").show();
    }

// find spending by date
    $("#findButtonS").click(function () {
        var element = $("#datepickerSpend").val();
        $.ajax({
            type: 'GET',
            url: "findSpending.html",
            data: {"date": element},
            contentType: "application/json; charset=UTF-8",
            dataType: 'json',
            success: function (response) {
                if (response.length == 0)
                    $("#historyTableSpend").hide();
                else
                    $("#historyTableSpend").show();
                spendingList = response;
                $("#historyTableSpend tbody tr").remove();
                var i = 0;
                $.each(spendingList, function () {
                    var tableRow = $("<tr />");
                    var tableColumn1 = $("<td />").text(spendingList[i].description);
                    var tableColumn2 = $("<td />").text(spendingList[i].sum);
                    var tableColumn3 = $("<td />").text(spendingList[i++].currency);
                    tableRow.append(tableColumn1, tableColumn2, tableColumn3);
                    $("#historyTableSpend").find("tbody:last").append(tableRow);
                    tableRow.click(function () {
                        $(this).addClass('highlight');
                    });
                });
            }
        });
    });
    // remove row from table
    $("#deleteButtonS").click(function () {
        var date = $("#datepickerSpend").val();
        var description = $("#historyTableSpend tbody tr.highlight td:eq(0)").html();
        var sum = $("#historyTableSpend tbody tr.highlight td:eq(1)").html();
        var currency = $("#historyTableSpend tbody tr.highlight td:eq(2)").html();
        $("#historyTableSpend tbody tr.highlight").remove();
        $.ajax({
            type: 'GET',
            url: "removeSpending.html",
            data: {"date": date,
                "description": description,
                "sum": sum,
                "currency": currency},
            contentType: "application/json; charset=UTF-8",
            dataType: 'json',
            success: function (response) {
            }
        });
    });
    // add form to table
    $("#addButtonS").click(function () {
        $("#popupSpend").show();
        var date = $("#datepickerAddSpend").val();
        var description = $("#descSpend").val();
        var sum = $("#sumSpend").val();
        var currency = $("#curSpend").val();
        if (currency === "1") {
            currency = "GRN.";
        } else if (currency === "2") {
            currency = "USD";
        } else
            currency = "EURO";

        $.ajax({
            type: 'GET',
            url: "addSpending.html",
            data: {"date": date,
                "description": description,
                "sum": sum,
                "currency": currency},
            contentType: "application/json; charset=UTF-8",
            dataType: 'json',
            success: function (response) {
                $("#historyTableSpend tbody tr").remove();
                $("#historyTableSpend").show();
                spendingList = response;
                var i = 0;
                $("#datepickerSpend").val(spendingList[i].date);
                $.each(spendingList, function () {
                    var tableRow = $("<tr />");
                    var tableColumn1 = $("<td />").text(spendingList[i].description);
                    var tableColumn2 = $("<td />").text(spendingList[i].sum);
                    var tableColumn3 = $("<td />").text(spendingList[i++].currency);
                    tableRow.append(tableColumn1, tableColumn2, tableColumn3);
                    $("#historyTableSpend").find("tbody:last").append(tableRow);
                    tableRow.click(function () {
                        $(this).addClass('highlight');
                    });
                });
            }
        });
        $("#popupSpend").hide();
    });
});
function PopUpShowSpend() {
    $("#popupSpend").show();
}
function PopUpHideSpend() {
    $("#popupSpend").hide();
}