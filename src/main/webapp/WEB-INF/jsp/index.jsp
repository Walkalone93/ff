<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Доходы и расходы</title>

        <script type="text/javascript" src="js/jquery-2.1.3.js"></script>

        <script type="text/javascript" src="datepicker/jquery-ui.js"></script>
        <link rel="stylesheet" href="datepicker/jquery-ui.css">

        <script type="text/javascript" src="js/myCalendar.js"></script>

        <script type="text/javascript" src="js/incoming.js"></script>
        <script type="text/javascript" src="js/spending.js"></script>

        <link rel="stylesheet" type="text/css" href="css/incoming.css">
        <link rel="stylesheet" type="text/css" href="css/spending.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>

    <body>
        
        <div id="menu">
            <h3><a href="http://localhost:8080/FamilyFinances/history.html">История</a></h3>
        </div>
        
        <!-----------------Incoming----------------->
        <div id="incoming"> 
            <h1>Вывести таблицу доходов</h1>
            <form method="POST">
                <h3>Выберите дату:
                    <input class="date" type="text" id="datepickerInc" name="date">
                    <input type="button" id="findButtonI" value="Поиск"></h3>

                <input type="button" value="Добавить" onclick="PopUpShowInc()">
                <input type="button" id="deleteButtonI" value="удалить">

            </form>

            <table id="historyTableInc"  value="${param.date}">
                <thead>
                    <tr>
                        <th>Описание</th>
                        <th>Сумма</th>
                        <th>Валюта</th>
                    </tr>
                </thead>

                <tbody>
                    <tr onclick="$(this).addClass('highlight')"> </tr>
                </tbody>
            </table>
        </div>

        <div class="b-popup" id="popupInc">
            <div class="b-popup-content">
                <h1>Добавить доход</h1>
                <form accept-charset="utf-8">
                    <p>Описание: 
                        <input type="text" id="descInc" size="38" value="${param.description}">
                    </p>

                    <div>Сумма: </div>
                    <input type="text" id="sumInc" value="${param.amount}">

                    Валюта:
                    <select name="currency"  id="curInc">
                        <option>${param.currency}</option>
                        <option value="1">GRN.</option>
                        <option value="2">USD</option>
                        <option value="3">EURO</option>
                    </select>

                    <p> <div id="date">Дата: </div>   
                    <input type="text" id="datepickerAddInc" name="date" value="${param.date}">
                    </p>

                    <p> <input type="button" id="addButtonI" value="Сохранить">
                        <input type="reset" id="deleteButton" value="удалить"></p>
                </form>
                <a href="javascript:PopUpHideInc()">Скрыть</a>
            </div>
        </div>

        <!-----------------Spending----------------->
        <div id="spending"> 

            <h1>Вывести таблицу расходов:</h1>
            <form action="spending.html" method="POST" >
                <h3>Выберите дату:
                    <input class="date" type="text" id="datepickerSpend" name="date">
                    <input type="button" id="findButtonS" value="Поиск"></h3>

                <input type="button" value="Добавить" onclick="PopUpShowSpend()">
                <input type="button" id="deleteButtonS" value="удалить">

            </form>

            <table id="historyTableSpend"  value="${param.date}">
                <thead>
                    <tr>
                        <th>Описание</th>
                        <th>Сумма</th>
                        <th>Валюта</th>
                    </tr>
                </thead>

                <tbody>
                    <tr onclick="$(this).addClass('highlight')"> </tr>
                </tbody>
            </table>
        </div>

        <div class="b-popup" id="popupSpend">
            <div class="b-popup-content">
                <h1>Добавить расход</h1>
                <form>
                    <p>Описание: 
                        <input type="text" id="descSpend" accept-charset="UTF-8" size="38" value="${param.description}">
                    </p>

                    <div>Сумма: </div>
                    <input type="text" id="sumSpend" value="${param.amount}">

                    Валюта:
                    <select name="currency"  id="curSpend">
                        <option>${param.currency}</option>
                        <option value="1">GRN.</option>
                        <option value="2">USD</option>
                        <option value="3">EURO</option>
                    </select>

                    <p> <div id="date">Дата: </div>   
                    <input type="text" id="datepickerAddSpend" name="date" value="${param.date}">
                    </p>

                    <p> <input type="button" id="addButtonS" value="Сохранить">
                        <input type="reset" id="deleteButton" value="удалить"></p>
                </form>
                <a href="javascript:PopUpHideSpend()">Скрыть</a>
            </div>
        </div>
    </body>

</body>
</html>
