const roomArr = document.querySelectorAll(".market-talk-wrap");
const partnerId = document.querySelectorAll(".partnerId");

roomArr.forEach((room, index) => {
    room.onclick = () => {
        room.href = `/market/talk/${partnerId[index].value}`;
    }
})
