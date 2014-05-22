<%@ include file="common/header.jsp" %>


<div class="link">
    <c:if test="${search == 'false'}">
        <h2>MASTER BILL</h2>
        <c:if test="${saved == 'success'}">
            <p class="success">MASTER BILL ENTRY Created Successfully</p>
        </c:if>
        <c:if test="${deleted == 'success'}">
            <p class="success">MASTER BILL ENTRY Deleted Successfully</p>
        </c:if>
        <c:if test="${status == 'exist'}">
            <p class="error">MASTER BILL ENTRY Already Exist</p>
        </c:if>
        <form:form modelAttribute="masterBill" action="/sphb/searchMasterBills" method="post">
            <table>
                <tr>
                    <td>
                        <form:label path="masterBillLadingNumber">MasterBillLadingNumber</form:label><form:input
                            path="masterBillLadingNumber"/>
                    </td>
                    <td>
                        <form:label path="houseBillLadingNumber">HouseBillLadingNumber</form:label><form:input
                            path="houseBillLadingNumber"/>
                    </td>
                    <td>
                        <form:label path="voyageNumber">VoyageNumber</form:label><form:input path="voyageNumber"/>
                    </td>
                    <td>
                        <form:label path="misc">Misc</form:label><form:input path="misc"/>
                    </td>
                </tr>
            </table>

            <c:if test="${admin == 'true'}">
                <button type="submit" id="save">Save MasterBill</button>
            </c:if>
            <button type="submit" id="search">Search MasterBill</button>

        </form:form>
    </c:if>
</div>

<c:if test="${search == 'true'}">
    <script>
        $(function () {
            ("#link").remove();
        });
    </script>

    <table id="dataTable">
        <tr>
            <th>MasterBillLadingNumber</th>
            <th>HouseBillLadingNumber</th>
            <th>VoyageNumber</th>
            <th>Misc</th>
            <c:if test="${admin == 'true'}">
                <th colspan="2">Actions</th>
            </c:if>
        </tr>
        <c:if test="${empty foundMasterBills}">
            <tr>
                <td colspan="4">No results found!</td>
            </tr>
        </c:if>
        <c:forEach var="current" items="${foundMasterBills}">
            <tr>
                <td>${current.masterBillLadingNumber}</td>
                <td>${current.houseBillLadingNumber}</td>
                <td>${current.voyageNumber}</td>
                <td>${current.misc}</td>
                <c:if test="${admin == 'true'}">
                    <td><a href="/sphb/editMasterBill/${current.masterBillLadingNumber}">Edit</a></td>
                    <td><a href="/sphb/deleteMasterBill/${current.masterBillLadingNumber}">Delete</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</c:if>

<script type="text/javascript">

    $(function () {
        $("#save").click(function (event) {
            $("#masterBill").attr("action", "/sphb/createMasterBill")
            $("#masterBill").submit();
        });

        $("#search").click(function (event) {
            $("#masterBill").attr("action", "/sphb/searchMasterBills")
            $("#masterBill").submit();

        });

        ("#dataTable").dataTable();
    });
</script>

<%@ include file="common/footer.jsp" %>
