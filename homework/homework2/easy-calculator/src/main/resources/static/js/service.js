function post() {
    var exp = document.getElementById('exp').value;
    $.post('calculate', JSON.stringify({
        expression: exp
    }), function (data, status, jqXHR) {
        document.getElementById('answer').innerText = data;
    }, 'application/json;charset=UTF-8');
}