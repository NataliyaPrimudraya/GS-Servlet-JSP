<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:myhtml title="Пользователи">
    <main>
        <table class="users-list">
            <thead>
            <tr>
                <th>ID</th>
                <th>Логин</th>
                <th>Email</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Дата рождения</th>
                <th>Роль</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <th>${user.id}</th>
                    <th>${user.login}</th>
                    <th>${user.email}</th>
                    <th>${user.surname}</th>
                    <th>${user.name}</th>
                    <th>${user.patronymic}</th>
                    <th>${user.birthdate}</th>
                    <th>${user.role}</th>
                    <th><a class="icon-link" title="редактировать"
                           href="<c:url value="/loginedit.jhtml?id=${user.id}"/>">&#9881;</a></th>
                    <th><a class="icon-link" title="удалить"
                           href="<c:url value="/loginedit.jhtml?id=${user.id}&action=delete"/>">&#128465;</a></th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="add-button">
            <button>
                <a href="<c:url value="/loginedit.jhtml?action=add"/>">Добавить пользователя</a>
            </button>
        </div>
    </main>
</t:myhtml>
