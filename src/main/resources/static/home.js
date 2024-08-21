function deleteAll() {
    if (confirm("정말 모든 글을 삭제하시겠습니까?")) {
        fetch('/api/delete', {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("모든 글이 삭제되었습니다.");
                    location.href = '/'; // 삭제 후 이동할 페이지 설정
                } else {
                    alert("삭제 중 오류가 발생했습니다.");
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("서버 오류가 발생했습니다.");
            });
    }
}