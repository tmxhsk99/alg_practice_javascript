const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";

let input = fs.readFileSync(filePath).toString().split("\n");
//console.log(input);
input = input[0];
//console.log(input);
input = input.split(" ").map((item) => +item);
//console.log(input);

solution(input[0], input[1]);

function solution(A, B) {
    console.log(A - B);
}
