function post() {
    var exp = document.getElementById('exp').value;
    $.post('calculate', {
        expression: exp
    }, function (data, status, jqXHR) {
        document.getElementById('answer').innerText = data;
    });
}