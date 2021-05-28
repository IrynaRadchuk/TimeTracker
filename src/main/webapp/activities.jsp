       <%@include file="templateLogged.jsp" %>

       <!doctype html>

         <head>
             <link rel="stylesheet" href="/css/background.css">
             <link rel="stylesheet" href="/css/profile.css">
         	</head>
         		<body>
         		<h2><b>Here you can see your activities and request for new ones</b></h2>
         		<hr>
       </div>
        <div class="card-body">
                            <div class="card-text">
                            <div> <label for="user_activities"><b>Approved activities</b></label></div>
                           <span> <c:set var="user_activities" value='${requestScope["user_activities"]}'/></span>
                            	<span><c:out value="${user_activities}"/></span>
                            	<hr>

  <label for="activity">Choose activity to approve:</label>
  <select name="activity" id="activity">
    <optgroup label="Swedish Cars">
      <option value="volvo">Volvo</option>
      <option value="saab">Saab</option>
    </optgroup>
    <optgroup label="German Cars">
      <option value="mercedes">Mercedes</option>
      <option value="audi">Audi</option>
    </optgroup>
  </select>
                                          <div class="card-text">
                                          <div> <label for="user_activities"><b>Approved activities</b></label></div>
                                         <span> <c:set var="user_activities" value='${requestScope["user_activities"]}'/></span>
                                          	<span><c:out value="${user_activities}"/></span>
                                          	<hr>
                                          	  <label for="all_activities">Choose activity to approve:</label>
                                                <select name="all_activities" id="all_activities">
                                      <c:forEach items="${all_activities}" var="activity">
                                       <option value="${activity}">
                                       ${activity}
                                        </option>
                                         </c:forEach>
                                                </select>
                            	  <form method="post" action="activities">
                                      <input class="button" type="submit" value="Request">
                                          </form>
                                          </body>
                                          </html>
                            </div>
                            </body>
                            </html>


