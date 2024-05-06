$(document).ready(function () {
    // 수량 증가 버튼 클릭 이벤트
    $(".increase-btn").click(function () {
        var $quantityInput = $(this).siblings(".quantity");
        var currentQuantity = parseInt($quantityInput.val());
        $quantityInput.val(currentQuantity + 1);
    });

    // 수량 감소 버튼 클릭 이벤트
    $(".decrease-btn").click(function () {
        var $quantityInput = $(this).siblings(".quantity");
        var currentQuantity = parseInt($quantityInput.val());
        if (currentQuantity > 1) {
            $quantityInput.val(currentQuantity - 1);
        }
    });
});