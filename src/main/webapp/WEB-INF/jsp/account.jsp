<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
<title>${requestScope.userLogin}</title>
     <s:url value="/css/style.css" var="mainCss" />
      <link href="${mainCss}" rel="stylesheet" />

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
          </script>
</head>

<script>


	$(document).ready(function() {


	checkContact();

$("#addContact").click(function(){


 $('#addContact').hide();
  $('#regContact').show();

             });

 $("#close").click(function(){


              $('#regContact').hide();
              $('#addContact').show();
                          });

$("#selectFilter").change(function () {
                getUserList();
                });


$("#textFilter").change(function () {
                getUserList();
                });
getUserList();


	});


	function getUserList() {

                             formData = {
                                   text: $("#textFilter").val(),
                                   type: $("#selectFilter").val(),
                             };

                          $.ajax({
                                          type:'GET',
                                          contentType:'application/json',
                                          url:'${sessionScope.userURL}',
                                          data: formData,
                                          dataType:'json',
                                          success: function (data, textStatus, jqXHR) {
                                         var allUsersArray = data.list;
                                         var output = '<table>'
                                         +'<tr>'
                                         +'<th>Name</th>'
                                         +'<th>Surname</th>'
                                         +'<th>Patronymic</th>'
                                         +'<th>phoneMobile</th>'
                                         +'<th>PhoneHome</th>'
                                         +'<th>Address</th>'
                                         +'<th>Email</th>'
                                          +'<th>Action</th>'
                                          +'</tr>';

                                              for(var i = 0; i<allUsersArray.length; i++){
                                                  var name = allUsersArray[i].name;
                                                   var surname = allUsersArray[i].surname
                                                   var patronymic = allUsersArray[i].patronymic
                                                   var phoneHome = allUsersArray[i].phoneHome
                                                   var phoneMobile = allUsersArray[i].phoneMobile
                                                   var address = allUsersArray[i].address
                                                   var email = allUsersArray[i].email

                                                   var rel = allUsersArray[i].links[0].rel;
                                                   var href = allUsersArray[i].links[0].href;
                                                   var rel2 = allUsersArray[i].links[1].rel;
                                                   var href2 = allUsersArray[i].links[1].href;

                                                  output += '<tr>'
                                                            +'<td>'

                                                          +'<input class="update" type="text"'
                                                                + 'id=' + '"' + href2 + '" '
                                                                + 'name=' + '"' + name + '" '
                                                                 +'value=' +'"'+name+'"'+ '/>'
                                                              +'</td>'
                                                              +'<td>'
                                                           +'<input class="update" type="text"'
                                                                + 'id=' + '"' + href2 + '" '
                                                                +'value=' +'"'+surname+'"'+ '/>'
                                                                +'</td>'
                                                                +'<td>'
                                                            +'<input class="update" type="text"'
                                                                + 'id=' + '"' + href2 + '" '
                                                                +'value=' +'"'+patronymic+'"'+ '/>'
                                                                +'</td>'
                                                                +'<td>'
                                                            +'<input class="update" type="text"'
                                                               + 'id=' + '"' + href2 + '" '
                                                                +'value=' +'"'+phoneMobile+'"'+ '/>'
                                                                +'</td>'
                                                                +'<td>'
                                                              +'<input class="update" type="text"'
                                                               + 'id=' + '"' + href2 + '" '
                                                                +'value=' +'"'+phoneHome+'"'+ '/>'
                                                                +'</td>'
                                                                +'<td>'
                                                               +'<input class="update" type="text"'
                                                               + 'id=' + '"' + href2 + '" '
                                                                +'value=' +'"'+address+'"'+ '/>'
                                                                +'</td>'
                                                                +'<td>'
                                                              +'<input class="update" type="text"'
                                                               + 'id=' + '"' + href2 + '" '
                                                                +'value=' +'"'+email+'"'+ '/>'
                                                                +'</td>'

                                                                +'<td>'
                                                          +'<input type="button" '
                                                          + 'class="removeUser" '
                                                          + 'name=' + '"' + name + '" '
                                                          + 'value=' + '"' + rel + '" '
                                                          + 'id=' + '"' + href + '" '
                                                          + '/>'
                                                          +'</td>'
                                                          +'</tr>'
                                                                                                        }
                                              output += '</table>';
                                              document.getElementById('contactList').innerHTML = output;

                                              var textArray = document.getElementsByClassName('update');

                                                for(var i = 0; i < textArray.length; i++){
                                                   textArray[i].addEventListener('change', updateUserContactHandler);
                                                }

                                                 var buttonArray = document.getElementsByClassName('removeUser');


                                                 for(var i = 0; i < buttonArray.length; i++){
                                                       buttonArray[i].addEventListener('click', removeUserContactHandler);

                                                                   }


                                          }
                                      });
                                      }


          function checkContact() {

          var s = '${requestScope.error}';
          if ((s=='successful registration')||(s=='')){
           $('#regContact').hide();}

          }


          function removeUserContactHandler() {

                      var href = $(this).attr('id');

                          $.ajax({
                type:'DELETE',
                contentType:'application/json',
                url:href,
                success:function (data, textStatus, jqXHR) {
                    getUserList();
                }
            });

                  }


      function updateUserContactHandler() {

                            var href = $(this).attr('id');

                                $.ajax({
                      type:'PATCH',
                      contentType:'application/json',
                      url:href,
                      success:function (data, textStatus, jqXHR) {
                          getUserList();
                      }
                  });

                        }



	</script>
<body>

<div id="welcome">

<span>${requestScope.userName}   ${requestScope.userSurname}<span>

</div>
<br>

<div id="addContact">

 <input  type="button" value="Add Contact"/>

</div>

<br>

<div id="filter">
Filter
 <select id ="selectFilter">
        <option value="0" selected="selected"> </option>
        <option value="1">by name</option>
        <option value="2">by surname</option>
        <option value="3">by phone</option>
 </select>

 <input id="textFilter"  type="text" value=""/>

</div>
<br>
<div id="exit">

 <a href="${s:mvcUrl('exit').build()}">

 <input  type="button" value="Exit"/>
 </a>


</div>


<div id = "error">
        ${requestScope.error}
   </div>

<div id="regContact">



    <sf:form modelAttribute="contact_registration">


     <fieldset>
         <div>
             <label for="name">
                 <s:message code="regCon.name"/>
             </label>
             <sf:input path="name"/>
             <sf:errors path="name" cssClass="error"/>
         </div>
         <div>
             <label for="surname">
                 <s:message code="regCon.surname"/>
             </label>
             <sf:input path="surname"/>
             <sf:errors path="surname" cssClass="error"/>
         </div>
         <div>
                             <label for="patronymic">
                                 <s:message code="regCon.patronymic"/>
                             </label>
                             <sf:input path="patronymic"/>
                             <sf:errors path="patronymic" cssClass="error"/>
                    </div>

         <div>
                    <label for="phoneMobile">
                 <s:message code="regCon.phoneMobile"/>
             </label>
             <sf:input path="phoneMobile" type="phoneMobile"/>
             <sf:errors path="phoneMobile" cssClass="error"/>


          </div>


         <div>
             <label for="phoneHome">
                 <s:message code="regCon.phoneHome"/>
             </label>
             <sf:input path="phoneHome" type="phoneHome"/>
             <sf:errors path="phoneHome" cssClass="error"/>
         </div>
         <div>
                      <label for="address">
                          <s:message code="regCon.address"/>
                      </label>
                      <sf:input path="address" type="address"/>
                      <sf:errors path="address" cssClass="error"/>
                  </div>
                  <div>
                               <label for="email">
                                   <s:message code="regCon.email"/>
                               </label>
                               <sf:input path="email" type="email"/>
                               <sf:errors path="email" cssClass="error"/>
                           </div>
         <div id ="text">
             <input type="submit" value="Submit"/>
             <input id="close" type="button" value="Close"/>
         </div>
     </fieldset>
 </sf:form>

</div>
<br>

<div id="contactList">
</div>


</body>
</html>