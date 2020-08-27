<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<div>
    <@l.logout />
</div>
<div>
    <form method="post">
        <input type="text" name="text" placeholder="Введіть повідомлення" />
        <input type="text" name="tag" placeholder="Тег" />
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Додати</button>
    </form>
</div>

<div>Список повідомлень</div>
<form method="post" action="filter">
    <input type="text" name="filter">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type = "submit">Знайти</button>
</form>
{#list messages as message}
    <div>
        <b>{message.id}</b>
        <span>{message.text}</span>
        <i>{message.tag}</i>
        <strong>{message.authorName}</strong>
    </div>
{#else}
No messages
{/#list}
</@c.page>