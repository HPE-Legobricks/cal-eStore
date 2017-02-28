<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<div class="usa-banner">
      <div class="usa-accordion">
        <header class="usa-banner-header">
          <div class="usa-grid usa-banner-inner"> <img src="img/favicons/favicon-57.png" alt="U.S. flag">
            <p>An official website of the United States government</p>
            <div class="loginbtn">
            
            
            <%
            if(!request.getAttribute("javax.servlet.forward.request_uri").equals("/calEStore/loginform")) { %>
            	<a href="${pageContext.request.contextPath}/loginform"> Sign in</a>
           <% }
            
    		%>
    
            		
            </div>
          </div>
        </header>
      </div>
    </div> 