module.exports = function(app,conn){
    app.get("/",function(req,res){
        var recieve = req.query.card_uid;
        var sql = 'SELECT CARD_UID FROM TB_USER WHERE CARD_UID =?'
        
        conn.query(sql,recieve,function(err,rows){
            var result ='';
            if(err){
                console.log(err)
            }else{
                if(rows.length!=0){
                    result = 'pass';
                }else{
                    result = 'fail';
                }
            }
            console.log(recieve+": "+ result)
            res.send(result)
        })
    })

    app.post('/arduino',function(req,res){
        var sql = "UPDATE TB_SEATINFO SET S3 = true WHERE TRAIN_NUM = 2001 AND BLOCK_NUM = '1-1'";
        
            var code = '404'
            conn.query(sql,function(err,rows){
                if(err){
                    console.log(err)
                }else{
                    code = '200'
                }
                res.send(code)
            });
        
    });

    app.post('/out',function(req,res){
        var sql = "UPDATE TB_SEATINFO SET S3 = false WHERE TRAIN_NUM = 2001 AND BLOCK_NUM = '1-1'";

            conn.query(sql,function(err,rows){
                var code = '404';
                if(err){
                    console.log(err)
                }else{
                    code = '200'
                }
                res.send('out :'+code)
            });
    });
}

