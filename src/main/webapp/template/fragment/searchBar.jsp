<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="search-bar ${param.location}">
	<form accept-charset="utf-8" class="form-horizontal" action="search" method="GET">
		<select class="entity-dropdown form-control all" name="t">
			<option value="all">All</option>
			<option value="gene">Genes</option>
			<option value="geneSet">Gene Sets</option>
			<option value="dataset">Datasets</option>
		</select>
		
		<!-- If you're wondering why the order of the elements is reversed, see
		     this simplified demo of how the search bar works:
		     http://jsfiddle.net/0z1uup9t/
		  -->
		<div class="submit-button input-group-btn">
			<button type="submit" class="btn btn-default">
				<span class="glyphicon glyphicon-search"></span>
			</button>
		</div>
		<div class="input-bar">
			<label for="q" class="sr-only">Search</label>
            <c:choose>
                <c:when test="${not empty param.userSearch}">
                    <input data-provide="typeahead" type="text" class="form-control" name="q" value="${param.userSearch}">
                </c:when>
                <c:otherwise>
                    <input data-provide="typeahead" type="text" class="form-control" name="q">
                </c:otherwise>
            </c:choose>
		</div>
	</form>
</div>
<c:if test="${param.location} == on-index-page">
	<div class="clear"></div>
</c:if>