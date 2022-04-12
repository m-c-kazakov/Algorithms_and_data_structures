var sum = function (num) { // функция для получения суммы цифр
        var
            str = num.toString(),
            arr = str.split(''),
            s = 0;
        arr.forEach(function (value) {
            s += parseInt(value);
        });
        return s;
    },
luckyTickets = function (n) {
    var
        maxSize = Math.pow(10, n),
        result = 0;
    for (var i = 0; i < maxSize; i++)
        for (j = 0; j < maxSize; j++)
            if (sum(i) == sum(j)) result++;
    return result;
};

console.log(luckyTickets(2)); //  4 816 030