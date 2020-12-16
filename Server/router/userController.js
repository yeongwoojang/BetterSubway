module.exports = function(app,conn,cookie){
    app.post("/android/signUp",function(req,res){
        var userName = req.body.userName;
        var userId = req.body.userId;
        var password = req.body.password;
        var params = [userName,userId,password];

        var sql = 'INSERT INTO TB_USER(USER_NAME,USER_ID,PASSWORD) VALUES(?,?,?)';

        conn.query(sql,params,function(err,rows){
            var code = '404';
        
            if(err){
                console.log(err);
            }else{
                code = '200';
                console.log(userName+'님이 회원가입하셨습니다.')
            }
            res.send(code)
        });
    });
    
    app.get("/android/signIn",function(req,res){
        var userId = req.query.userId
        var password = req.query.password

        var params = [userId,password]
        
        var sql = 'SELECT *FROM TB_USER WHERE USER_ID = ? AND PASSWORD = ?'

        conn.query(sql,params,function(err,rows){
            var code = '404';
            if(err){
                console.log(err)
            }else{
                if(rows.length!=0){
                    code ='200';
                    res.cookie('user_ID',userId);
                    console.log('로그인에 성공했습니다.')
                }else{
                    console.log('로그인에 실패했습니다.')
                }
            }
            res.send(code)
        })

    });
    app.post("/android/addbookMarkStation",function(req,res){
        var userId = '';
        if (req.headers.cookie == null) {
            console.log('null')
        } else {
            console.log(req.headers.cookie)
            cookies = cookie.parse(req.headers.cookie);
        }

        userId = cookies.user_ID
        var station = req.body.station;
        var params = [userId,station];

        var sql = 'INSERT INTO TB_BOOKMARK_STATION(USER_ID,STATION) VALUES(?,?)'
        
        conn.query(sql,params,function(err,rows){
            var code = '404';
            
            if(err){
                console.log(err)
            }else{
                code = '200';
                console.log('즐겨찾기에 역을 추가했습니다.')
            }
        res.send(code);

        })
    });

    app.delete("/android/delBookMarkStation",function(req,res){
        var userId = '';
        if (req.headers.cookie == null) {
            console.log('null')
        } else {
            console.log(req.headers.cookie)
            cookies = cookie.parse(req.headers.cookie);
        }
        userId = cookies.user_ID
        var station = req.query.station;
        var params = [userId,station];

        var sql = 'DELETE FROM TB_BOOKMARK_STATION WHERE USER_ID=? and STATION=?'

        conn.query(sql,params,function(err,rows){
            var code = '404';
            if(err){
                console.log(err);
            }else{
                if(rows.length!=0){
                    code = '200';
                    console.log('해당 북마크를 삭제합니다.');
                }else{
                    console.log('해당 북마크가 없어 삭제하지 못했습니다.'); 
                }
            }
            res.send(code);
        });
    });

    app.get("/android/chkBookMarkStation",function(req,res){
        var userId = '';
        if (req.headers.cookie == null) {
            console.log('null')
        } else {
            console.log(req.headers.cookie)
            cookies = cookie.parse(req.headers.cookie);
        }
        userId = cookies.user_ID
        console.log(userId);
        var station = req.query.station;
        var params = [userId,station];
        console.log(station);
        var sql = 'SELECT *FROM TB_BOOKMARK_STATION WHERE USER_ID=? and STATION=?'

        conn.query(sql,params,function(err,rows){
            var code = '404';
            if(err){
                console.log(err);
            }else{
                if(rows.length!=0){
                    code = '200';
                    console.log('해당 북마크가 있습니다.');
                }else{
                    console.log('해당 북마크가 없습니다.'); 
                }
            }
            res.send(code);
        });
    });

    app.get("/android/getBookMarkList",function(req,res){
        var userId = '';
        if (req.headers.cookie == null) {
            console.log('null')
        } else {
            console.log(req.headers.cookie)
            cookies = cookie.parse(req.headers.cookie);
        }
        userId = cookies.user_ID
        console.log(userId);
        var sql = 'SELECT *FROM TB_BOOKMARK_STATION WHERE USER_ID=?';

        conn.query(sql,userId,function(err,rows){
            var code = 404;
            var jsonArray='';

            if(err){
                console.log(err)
            }else{
                if(rows.length!=0){
                    code =200;
                    jsonArray = rows;
                    console.log('유저의 즐겨찾기 목록을 불러왔습니다.');
                }else{
                    jsonArray = null;
                    console.log('유저의 즐겨찾기 목록을 불러오지 못했습니다.');
                }
            }
            res.json({
                'code' : code,
                'jsonArray' : jsonArray
            })
        });

    })
















}
