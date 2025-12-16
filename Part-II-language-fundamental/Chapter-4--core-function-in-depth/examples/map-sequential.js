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

var map = function (list, transform) {
      if (list == null) {
          return null;
      } else {
          return cons(transform(first(list)), map(rest(list), transform));
      }
  };

// first(
//     map(node1, function(val) {return val + " mapped!"})
// );

console.log(first(
    map(node1, function(val) {return val + " mapped!";})
));


console.log(first(
    map(node2, function(val) {return val + " mapped!";})
));

console.log(first(
    map(node3, function(val) {return val + " mapped!";})
));

console.log(rest(
    map(node1, function(val) {return val + " mapped!";})
));
