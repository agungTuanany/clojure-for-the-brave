var first = function (array) {
    return array[0];
};

var rest = function (array) {
    var sliced = array.slice(1, array.length);
    if (sliced.length == 0) {
        return null;
    } else {
        return sliced;
    }
};

var cons = function (newValue, array) {
    return [newValue].concat(array);
};


var map = function (list, transform) {
    if (list == null) {
        return null;
    } else {
        return cons(transform(first(list)), map(rest(list), transform));
    }
};

var list = ["Translvania", "Forks", "WA"];

map(list, function (val) {return val + " mapped!"});

console.log(map(list, function (val) {return val + " mapped!"}));
