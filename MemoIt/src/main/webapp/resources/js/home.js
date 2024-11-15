document.addEventListener("DOMContentLoaded", () => {
    const toggleButtons = document.querySelectorAll(".toggle-btn");

    // 페이지 로드 시 localStorage에서 열림/닫힘 상태를 불러오기
    toggleButtons.forEach(button => {
        const categoryTitle = button.closest(".category-title").textContent.trim();
        const memoItems = button.closest(".memo-category").querySelector(".memo-items");
        const isOpen = localStorage.getItem(`category-${categoryTitle}`);

        if (isOpen === "true") {
            memoItems.style.display = "block";
            button.textContent = "-";  // 화살표 방향 변경
        } else {
            memoItems.style.display = "none";
            button.textContent = "+";
        }

        // 버튼 클릭 이벤트
        button.addEventListener("click", (event) => {
            if (memoItems.style.display === "none" || memoItems.style.display === "") {
                memoItems.style.display = "block";
                event.target.textContent = "-";
                localStorage.setItem(`category-${categoryTitle}`, "true");  // 열림 상태 저장
            } else {
                memoItems.style.display = "none";
                event.target.textContent = "+";
                localStorage.setItem(`category-${categoryTitle}`, "false"); // 닫힘 상태 저장
            }
        });
    });
});
