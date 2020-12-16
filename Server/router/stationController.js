module.exports = function(app,conn){
    app.get("/android/stationInfo",function(req,res){
        var line = req.query.line;
        
        console.log("success")
        var sql = 'SELECT *FROM TB_STATION WHERE LINE =? ORDER BY S_NAME'
        conn.query(sql,line,function(err,rows){
            var code = 404;
            var jsonArray ='';
            if(err){
                console.log(err)
            }else{
                if(rows.length!=0){
                    code = 200;
                    jsonArray = rows;
                    console.log('자하철역 정보를 불러왔습니다.')
                }else{
                    console.log('지하철역 정보를 불러오지 못했습니다.')
                }
            }
            res.json({
                'code' : code,
                'jsonArray' : jsonArray
            })

        })
    });

    app.get("/android/arrivalTime",function(req,res){
        
        // var line = req.query.line
        var station = req.query.station

        params =[station]

        var sql = 'SELECT S.S_CODE,T.STATION,(SELECT S_CODE FROM TB_STATION WHERE S_NAME =T.DIRECTION) DS_CODE ,T.DIRECTION,T.ARRIVAL_TIME,T.TRAIN_NUM,T.PATH FROM '
        +'TB_TRAIN_TIMETABLE T JOIN TB_STATION S '
        +'ON T.STATION = S.S_NAME '
        +'WHERE T.STATION=?'


        conn.query(sql,params,function(err,rows){
            var code = 404;
            var jsonArray='';
            
            if(err){
                console.log(err)
            }else{
                if(rows.length>2){
                    code=200;
                    jsonArray = rows;
                    console.log('지하철 시간정보를 불러왔습니다.');
                }else{
                    code=404;
                    jsonArray =null;
                    console.log('지하철 시간정보를 불러오지 못했습니다.');
                }
            }
            res.json({
                'code' : code,
                'jsonArray' : jsonArray
            })
        })
    });

    app.get("/android/seatInfo",function(req,res){
        var trainNum = req.query.trainNum;
        var blockNum = req.query.blockNum;
        
        var params =[trainNum,blockNum]
        var sql = 'SELECT *FROM TB_SEATINFO WHERE TRAIN_NUM =? AND BLOCK_NUM=?'

        conn.query(sql,params,function(err,rows){
            var code = 404;
            var jsonArray = '';

            if(err){
                console.log(err)
            }else{
                if(rows.length!=0){
                    code = 200;
                    jsonArray = rows;
                    console.log('좌석정보를 불러왔습니다.');
                }else{
                    code = 404;
                    jsonArray ='';
                    console.log('좌석정보를 불러오지못했습니다.');
                }
            }
            res.json({
                'code' : code,
                'jsonArray' :jsonArray
            })
        });

    });

    app.get("/android/searchStation",function(req,res){
        var station = req.query.station;

        var sql = 'SELECT * FROM TB_STATION WHERE S_NAME = ?';

        conn.query(sql,station,function(err,rows){
            var code = 404;
            var jsonArray='';

            if(err){
                console.log(err)
            }else{
                if(rows.length!=0){
                    code =200;
                    jsonArray = rows;
                    console.log('검색한 역을 찾았습니다.');
                }else{
                    code = 404;
                    console.log('검색한 역을 찾지못했습니다.');
                    jsonArray = null;
                }
            }
            res.json({
                'code' : code,
                'jsonArray' : jsonArray
            })
        });
    });

   
}





