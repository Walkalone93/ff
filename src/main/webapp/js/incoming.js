$(document).ready(function () {

    $("#historyTableInc").hide(); // start without table
    $("#popupInc").hide();
    function PopUpShowInc() {
        $("#popupInc").show();
    }

// find spending by date
    $("#findButtonI").click(function () {
        var element = $("#datepickerInc").val();
        $.ajax({
            type: 'GET',
            url: "findIncoming.html",
            data: {"date": element},
            contentType: "application/json; charset=UTF-8",
            dataType: 'json',
            success: function (response) {
                console.log(response);
                if (response.length == 0)
                    $("#historyTableInc").hide();
                else
                    $("#historyTableInc").show();
                spendingList = response;
                $("#historyTableInc tbody tr").remove();
                var i = 0;
                $.each(spendingList, function () {
                    var tableRow = $("<tr />");
                    var tableColumn1 = $("<td />").text(spendingList[i].description);
                    var tableColumn2 = $("<td />").text(spendingList[i].sum);
                    var tableColumn3 = $("<td />").text(spendingList[i++].currency);
                    tableRow.append(tableColumn1, tableColumn2, tableColumn3);
                    $("#historyTableInc").find("tbody:last").append(tableRow);
                    tableRow.click(function () {
                        $(this).addClass('highlight');
                    });
                });
            }
        });
    });
    // remove row from table
    $("#deleteButtonI").click(function () {
        var date = $("#datepickerInc").val();
        var description = $("#historyTableInc tbody tr.highlight td:eq(0)").html();
        var sum = $("#historyTableInc tbody tr.highlight td:eq(1)").html();
        var currency = $("#historyTableInc tbody tr.highlight td:eq(2)").html();
        $("#historyTableInc tbody tr.highlight").remove();
        $.ajax({
            type: 'GET',
            url: "removeIncoming.html",
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
    $("#addButtonI").click(function () {
        $("#popupInc").show();
        var date = $("#datepickerAddInc").val();
        var description = $("#descInc").val();
        var sum = $("#sumInc").val();
        var currency = $("#curInc").val();
        if (currency === "1")
            currency = "GRN.";
        else if (currency === "2")
            currency = "USD";
        else
            currency = "EURO";

        $.ajax({
            type: 'GET',
            url: "addIncoming.html",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: {"date": date,
                "description": description,
                "sum": sum,
                "currency": currency},
            success: function (response) {
                console.log("addIncoming");
                $("#historyTableInc tbody tr").remove();
                $("#historyTableInc").show();
                spendingList = response;
                var i = 0;
                $("#datepickerInc").val(spendingList[i].date);
                $.each(spendingList, function () {
                    console.log(spendingList[i].description);
                    var tableRow = $("<tr />");
                    var tableColumn1 = $("<td />").text(spendingList[i].description);
                    var tableColumn2 = $("<td />").text(spendingList[i].sum);
                    var tableColumn3 = $("<td />").text(spendingList[i++].currency);
                    tableRow.append(tableColumn1, tableColumn2, tableColumn3);
                    $("#historyTableInc").find("tbody:last").append(tableRow);
                    tableRow.click(function () {
                        $(this).addClass('highlight');
                    });
                });
            }
        });
        $("#popupInc").hide();
    });
});
function PopUpShowInc() {
    $("#popupInc").show();
}
function PopUpHideInc() {
    $("#popupInc").hide();
}