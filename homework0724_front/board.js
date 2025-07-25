function insertBoard() {
    const title = document.querySelector("#title-input").value.trim();
    const content = document.querySelector("#content-input").value.trim();
    const categoryNo = document.querySelector("#categoryNo-input").value.trim();

    if (!title || !content || !categoryNo) {
        alert("모든 항목을 입력하세요.");
        return;
    }

    const vo = {
        title,
        content,
        categoryNo: Number(categoryNo)
    };

    fetch("http://127.0.0.1:8080/api/board", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(vo)
    })
    .then(resp => resp.text())
    .then(() => {
        location.reload();
    })
    .catch(err => {
        console.log(err);
        alert("게시글 등록 실패");
    });
}

function selectList() {
    fetch("http://127.0.0.1:8080/api/board")
    .then(resp => resp.json())
    .then(voList => {
        const tbody = document.querySelector("#tbody");
        let str = "";
        for (const vo of voList) {
            str += `
            <tr onclick="boardListByNo(${vo.no})" style="cursor:pointer;">
                <td>${vo.no}</td>
                <td>${vo.title}</td>
                <td>${vo.content}</td>
            </tr>`;
        }
        tbody.innerHTML = str;
    })
    .catch(err => {
        console.log(err);
        alert("게시글 조회 실패");
    });
}

function boardListByNo(no) {
    fetch(`http://127.0.0.1:8080/api/board/${no}`)
    .then(resp => resp.json())
    .then(vo => {
        document.querySelector("#no").value = vo.no;
        document.querySelector("#title").value = vo.title;
        document.querySelector("#content").value = vo.content;
        document.querySelector("#categoryName").value = vo.categoryName || "";
        document.querySelector("#categoryNo").value = vo.categoryNo || "";
    })
    .catch(err => {
        console.log(err);
        alert("상세 조회 실패");
    });
}

function updateBoard() {
    const no = document.querySelector("#no").value;
    const title = document.querySelector("#title").value.trim();
    const content = document.querySelector("#content").value.trim();
    const categoryNo = document.querySelector("#categoryNo").value;

    if (!no) {
        alert("수정할 게시글을 선택하세요.");
        return;
    }
    if (!title || !content || !categoryNo) {
        alert("모든 항목을 입력하세요.");
        return;
    }

    const vo = {
        no: Number(no),
        title,
        content,
        categoryNo: Number(categoryNo)
    };

    fetch("http://127.0.0.1:8080/api/board", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(vo)
    })
    .then(resp => resp.text())
    .then(() => {
        alert("게시글이 수정되었습니다.");
        location.reload();
    })
    .catch(err => {
        console.log(err);
        alert("게시글 수정 실패");
    });
}

function deleteBoard() {
    const no = document.querySelector("#no").value;
    if (!no) {
        alert("삭제할 게시글을 선택하세요.");
        return;
    }

    if (!confirm("정말 삭제하시겠습니까?")) return;

    fetch(`http://127.0.0.1:8080/api/board/${no}`, {
        method: "DELETE"
    })
    .then(resp => resp.text())
    .then(() => {
        alert("게시글이 삭제되었습니다.");
        location.reload();
    })
    .catch(err => {
        console.log(err);
        alert("게시글 삭제 실패");
    });
}

selectList();