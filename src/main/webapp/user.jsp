<%@include file="templateLogged.jsp" %>
<!doctype html>
<head>
   <title>Activities Tracker</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
   <link rel="stylesheet" href="/css/style.css">
   <link rel="stylesheet" href="/css/background.css">
</head>
<body>
   <br/>
   <div class="container">
      <div class="row justify-content-center">
         <div class="col-md-6 text-center mb-5">
            <h2 class="heading-section">Activities Tracker</h2>
         </div>
      </div>
      <div class="row">
         <div class="col-md-12">
            <div class="content w-100">
               <div class="calendar-container">
                  <div class="calendar">
                     <div class="year-header">
                        <span class="left-button fa fa-chevron-left" id="prev"> </span>
                        <span class="year" id="label"></span>
                        <span class="right-button fa fa-chevron-right" id="next"> </span>
                     </div>
                     <table class="months-table w-100">
                        <tbody>
                           <tr class="months-row">
                              <td class="month">Jan</td>
                              <td class="month">Feb</td>
                              <td class="month">Mar</td>
                              <td class="month">Apr</td>
                              <td class="month">May</td>
                              <td class="month">Jun</td>
                              <td class="month">Jul</td>
                              <td class="month">Aug</td>
                              <td class="month">Sep</td>
                              <td class="month">Oct</td>
                              <td class="month">Nov</td>
                              <td class="month">Dec</td>
                           </tr>
                        </tbody>
                     </table>
                     <table class="days-table w-100">
                        <td class="day">Sun</td>
                        <td class="day">Mon</td>
                        <td class="day">Tue</td>
                        <td class="day">Wed</td>
                        <td class="day">Thu</td>
                        <td class="day">Fri</td>
                        <td class="day">Sat</td>
                     </table>
                     <div class="frame">
                        <table class="dates-table w-100">
                           <tbody class="tbody">
                           </tbody>
                        </table>
                     </div>
                     <button class="button" id="add-button">Add Activity</button>
                  </div>
               </div>
               <div class="events-container">
               </div>
               <div class="dialog" id="dialog">
                  <h2 class="dialog-header"> Add Activity </h2>
                  <form class="form" id="form" method="post" action="${pageContext.request.contextPath}/tracker/user">
                     <div class="form-container" align="center">
                        <label class="form-label" id="valueFromMyButton" for="name">Activity Name</label>
                        <input class="input" type="text" id="name" name="name" maxlength="36">
                        <label class="form-label" id="valueFromMyButton" for="count">Time Duration (hours)</label>
                        <input class="input" type="number" id="count" name="count" min="0" max="8" maxlength="1">
                        <input type="button" value="Cancel" class="button" id="cancel-button">
                        <input type="submit" value="OK" class="button button-white" id="ok-button">
                     </div>
                     <input class="input" type="hidden" id="date" name="date">
                     <input class="input" type="hidden" id="day" name="day">
               </div>
               </form>
            </div>
         </div>
      </div>
   </div>
   </div>
   </section>
   <script src="/js/jquery.min.js"></script>
   <script src="/js/popper.js"></script>
   <script src="/js/bootstrap.min.js"></script>
   <script src="/js/main.js"></script>
</body>
</html>