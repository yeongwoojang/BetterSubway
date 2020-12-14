package com.example.better_subway.view.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.better_subway.R
import com.example.better_subway.model.vo.Arrival
import com.example.better_subway.view.adapter.LeftArrivalAdapter
import com.example.better_subway.view.adapter.RightArrivalAdapter
import com.example.better_subway.viewmodel.TimeTableViewModel
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_time_table.*

@AndroidEntryPoint
class TimeTableActivity : AppCompatActivity(), View.OnClickListener {

    var choiceTrainNum : Int =0
    var isBookMark = false
    private val viewModel by viewModels<TimeTableViewModel>()
    private var arrivalList = arrayListOf<Arrival>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table)

        val intnet = intent
        if (intent != null) {
            arrivalList = intent.getSerializableExtra("arrivalList") as ArrayList<Arrival>
        }
        //가는 방향에 따라 지하철 도착정보를 나눈다.
        viewModel.divideTimeTable(arrivalList)

        slidingView.panelHeight = 0
        //선택한 역 화면에 뿌려주기
        station_name.text = arrivalList[0].station


        val lefttAdapter = LeftArrivalAdapter(object : LeftArrivalAdapter.leftArrivalAdtListener {
            override fun request(trainNum : Int) {
                choiceTrainNum = trainNum
                slidingView.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
            }
        })
        val rightAdapter = RightArrivalAdapter(object : RightArrivalAdapter.RightArrivalAdtListener{
            override fun request(trainNum: Int) {
                choiceTrainNum = trainNum
                slidingView.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
            }
        })
        left_recyclerView.apply {
            this.layoutManager =
                LinearLayoutManager(this@TimeTableActivity, RecyclerView.VERTICAL, false)
            this.adapter = lefttAdapter
        }
        right_recyclerView.apply {
            this.layoutManager =
                LinearLayoutManager(this@TimeTableActivity, RecyclerView.VERTICAL, false)
            this.adapter = rightAdapter
        }

        lefttAdapter.updateItems(viewModel.leftArrivalList)
        rightAdapter.updateItems(viewModel.rightArrivalList)

        //왼쪽 목적지 화면에 뿌려주기
        left_direction.text = viewModel.leftArrivalList[0].direction
        right_direction.text = viewModel.rightArrivalList[0].direction


        viewModel.chkBookMarkStation(arrivalList[0].station)
        viewModel.cBmkLiveData.observe(this, Observer {
            if(it=="404"){
                var color = ContextCompat.getColor(this, R.color.gray400)
                bookmark_img.setColorFilter(color)
                isBookMark = false
            }else{
                var color = ContextCompat.getColor(this, R.color.yellow400)
                bookmark_img.setColorFilter(color)
                isBookMark = true
            }
        })


        bookmark_img.setOnClickListener {
            //북마크 설정안되어있을 시
            if(!isBookMark){
                var color = ContextCompat.getColor(this, R.color.yellow400)
                bookmark_img.setColorFilter(color)
                viewModel.addBookMarkStation(arrivalList[0].station)
                isBookMark = true
            }else{ //북마크 설정 되어있을 시
                var color = ContextCompat.getColor(this, R.color.gray400)
                bookmark_img.setColorFilter(color)
                viewModel.delBookMarkStation(arrivalList[0].station)
                isBookMark =false
            }


        }
        viewModel.addBmkLiveData.observe(this, Observer {

        })

        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.seat2)
        seat7.setImageBitmap(bitmap)
        seat8.setImageBitmap(bitmap)
        seat9.setImageBitmap(bitmap)
        seat10.setImageBitmap(bitmap)
        seat11.setImageBitmap(bitmap)
        seat12.setImageBitmap(bitmap)
        val sideInversion = Matrix()
        sideInversion.setScale((-1).toFloat(), 1F)
        val slideInversionImg: Bitmap =
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, sideInversion, false)
        seat7.setImageBitmap(slideInversionImg)
        seat8.setImageBitmap(slideInversionImg)
        seat9.setImageBitmap(slideInversionImg)
        seat10.setImageBitmap(slideInversionImg)
        seat11.setImageBitmap(slideInversionImg)
        seat12.setImageBitmap(slideInversionImg)
        var color = ContextCompat.getColor(this, R.color.red300)
//        seat12.setColorFilter(color)

        viewModel.seatLiveData.observe(this, Observer { seatList ->
            val color = ContextCompat.getColor(this, R.color.red300)
            val seat = seatList[0]
                if (seat.s1==1) {
                    seat1.setColorFilter(color)
                }
                if (seat.s2==1) {
                    seat2.setColorFilter(color)
                }
                if (seat.s3==1) {
                    seat3.setColorFilter(color)
                }
                if (seat.s4==1) {
                    seat4.setColorFilter(color)
                }
                if (seat.s5==1) {
                    seat5.setColorFilter(color)
                }
                if (seat.s6==1) {
                    seat6.setColorFilter(color)
                }
                if (seat.s7==1) {
                    seat7.setColorFilter(color)
                }
                if (seat.s8==1) {
                    seat8.setColorFilter(color)
                }
                if (seat.s9==1) {
                    seat9.setColorFilter(color)
                }
                if (seat.s10==1) {
                    seat10.setColorFilter(color)
                }
                if (seat.s11==1) {
                    seat11.setColorFilter(color)
                }
                if (seat.s12==1) {
                    seat12.setColorFilter(color)
                }
            cardView.visibility = View.VISIBLE

        })
        one_and_one_bt.setOnClickListener(this)
        one_and_two_bt.setOnClickListener(this)
        one_and_three_bt.setOnClickListener(this)
        one_and_four_bt.setOnClickListener(this)

        slidingView.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener{
            override fun onPanelSlide(panel: View?, slideOffset: Float) {
            }

            override fun onPanelStateChanged(
                panel: View?,
                previousState: SlidingUpPanelLayout.PanelState?,
                newState: SlidingUpPanelLayout.PanelState?
            ) {
                if(slidingView.panelState==SlidingUpPanelLayout.PanelState.COLLAPSED){
                    cardView.visibility = View.INVISIBLE
                }
            }
        })

    }


    fun resetSeatInfo() {
        val color = ContextCompat.getColor(this, R.color.black)
        seat1.setColorFilter(color)
        seat2.setColorFilter(color)
        seat3.setColorFilter(color)
        seat4.setColorFilter(color)
        seat5.setColorFilter(color)
        seat6.setColorFilter(color)
        seat7.setColorFilter(color)
        seat8.setColorFilter(color)
        seat9.setColorFilter(color)
        seat10.setColorFilter(color)
        seat11.setColorFilter(color)
        seat12.setColorFilter(color)
    }

    override fun onClick(v: View?) {
        resetSeatInfo()
        viewModel.getSeatInfo(choiceTrainNum,v?.tag.toString())
    }
}