/**
 * 시간 제한    메모리 제한    제출    정답    맞힌 사람    정답 비율
 * 1 초    256 MB    383977    180110    154085    47.224%
 * 문제
 * 두 자연수 A와 B가 주어진다. 이때, A+B, A-B, A*B, A/B(몫), A%B(나머지)를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 두 자연수 A와 B가 주어진다. (1 ≤ A, B ≤ 10,000)
 *
 * 출력
 * 첫째 줄에 A+B, 둘째 줄에 A-B, 셋째 줄에 A*B, 넷째 줄에 A/B, 다섯째 줄에 A%B를 출력한다.
 */
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";

let input = fs.readFileSync(filePath).toString().split("\n");
input = input[0];
input = input.split(" ").map((item) => +item);

solution(input[0], input[1]);

function solution(A, B) {
    console.log(A + B);
    console.log(A - B);
    console.log(A * B);
    console.log(Math.floor(A / B));
    console.log(A % B);
}
