// using first, rest, cons



var node1 = {
    value: "first",
    next: node2
};

var node2 = {
    value: "middle",
    next: node3
};

var node3 = {
    value: "last",
    next: null
};

var first = function(node) {
    return node.value;
};

var rest = function(node) {
    return node.next;
};

var cons = function(newValue, node) {
    return {
        value: newValue,
        next: node
    };
};

first(node1);

// console.log(first(node1));
// console.log(rest(node2));
// console.log(cons(4, node3));

console.log(first(node3));
// console.log(rest(node1));
