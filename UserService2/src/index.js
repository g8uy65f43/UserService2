import liff from '@line/liff'
window.onload = function (e) {
    liff
    .init({liffId: "2002542651-30wDwKnW"})
    .then(() => {
        // 初期化完了
        initializeApp();
        const openId = liff.getIDToken();
        const accessToken=liff.getAccessToken();
        const os = liff.getOS();
        const jsonData = JSON.stringify({
          openId: openId,
          accessToken:accessToken,
          os:os
        });

        fetch('http://localhost/user/user/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: jsonData,
            credentials: 'same-origin' // 正確的拼寫
        })
            .then(res => {
                // 這裡處理回應
            })
            .catch(e => console.log(e));
        


    })

    function initializeApp() {
        // ログインチェック
        if (liff.isLoggedIn()) {
            //ログイン済
            getLineData();
        } else {
            // 未ログイン
            let result = window.confirm("LINE Loginしますか？");
            if(result) {
                liff.login();
            }
        }
    }
    function getLineData() {
        liff.getProfile()
        .then(profile => {
          console.log("ログインしてるユーザーのid:" + profile.userId);
          console.log("ログインしてるユーザーの名前:" + profile.displayName);
          console.log("ログインしてるユーザーの画像URL:" + profile.pictureUrl);
        })
    }
        const txt1 =  document.querySelector("#txt1")
        const btn1 = document.querySelector("#btn1")
    btn1.addEventListener("click",e=>{
    
        alert("@")
    
    })
    
        




};




   