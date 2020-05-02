<template>
	<view>
		<view class="head">
			<view class="tv">
				<video autoplay object-fit="fill" show-mute-btn :title="tname" @error="error" :src="src"></video>
			</view>
			<view class="info">
				<text>{{tname}}</text>
			</view>
		</view>
		<view class="main">
			<qs-tabs :tabs="sortNames" :current="current" animationLineWidth="100" activeColor="#ef5656" @change="change" backgroundColor="#FFFFFF"></qs-tabs>
			<view class="tvs">
				<view v-for="(item,index) in tvs" :key="item.tid" :class="active == item.tid?'active':'tv'" @click="updateSrc(item.tsrc,item.tname,item.tid)">{{item.tname}}</view>
			</view>
			<view class="falg" v-if="falg">更多节目敬请期待~~~</view>
		</view>
	</view>
</template>

<script>
	import QsTabs from '@/components/QS-tabs/QS-tabs.vue';
	export default {
		data() {
			return {
				src: "http://39.135.34.150:8080/000000001000/1000000001000021973/1.m3u8?channel-id=ystenlive&Contentid=1000000001000021973&livemode=1&stbId=005203FF000360100001001A34C0CD33&userToken=4ef1f6fdd53988bdf19472c73151206f21vv&usergroup=g21077200000&version=1.0&owaccmark=1000000001000021973&owchid=ystenlive&owsid=1106497909461769539&AuthInfo=yOLXJswzZFfV3FvB8MhHuElKGJKLbU5H0jB3qAhfSE7AORAoVDZDWbFnJ0sXJEaRLaQJeR5usCQMKdpIDCZAoYPt4bOuuiUwGxs8%2fKxpb7Wa3xqB26AcGEvjhx3JJlw6"
				,tname: "CCTV-1综合",
				sortNames: [],
				sorts: [],
				tvs: [],
				active: 1,
				current: 0, 
				pageNum: 1,
				pageSize: 20,
				falg : false
			}
		},
		onLoad() {
			this.getSorts()
			this.getTv(1,this.pageNum,this.pageSize)
		},
		methods: {
			getSorts(){
				uni.request({
					url:"http://beloved.ink:9090/api/sort",
					header: {
						'Content-Type': 'application/json;charset=UTF-8;'
					},
					success: (res) => {
						console.log(res)
						this.sorts = res.data
						for(let data of res.data) {
							this.sortNames.push(
								{name:data.sname}
							)
						}
					}
				})
			},

 
			getTv(sid,pageNum,pageSize){
				uni.request({
					url:"http://beloved.ink:9090/api/tvsrc/"+sid+"/"+pageNum+"/"+pageSize,
					header: {
						'Content-Type': 'application/json;charset=UTF-8;'
					},
					success: (res) => {
						console.log(res)
						this.tvs = [...this.tvs,...res.data.list];
					}
				})
			},
			updateSrc(src,name,tid){
				this.src = src
				this.tname = name
				this.active = tid
			},
			change(index){
				console.log(index)
				this.current = index;
				let sid = this.sorts[index].sid;
				this.tvs = []; 
				this.pageNum = 1;
				this.getTv(sid,this.pageNum,this.pageSize)
			},
			error(){
				uni.showModal({
					title: '抱歉',
					content: '播放出错了，是否提交反馈！！！',
					cancelText: "否",
					confirmText: "是",
					success: function (res) {
						if (res.confirm) {
							console.log('用户点击确定');
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})
			}
			
		},
		components: { 
			QsTabs
		},
		onReachBottom() {
			if(this.pageSize*this.pageNum > this.tvs.length){
				this.falg = true
				return false;
			}
			console.log("页面触底，查询下一页数据")
			this.pageNum++;
			let sid = this.sorts[this.current].sid;
			this.getTv(sid,this.pageNum,this.pageSize)
		}
	}
</script>

<style lang="scss">
	page{
		background-color: #f2f2f2;
	}
	.head{
		width: 750rpx;
		height: 480rpx;
		.tv{
			height: 400rpx;
			/* border: 10rpx solid #000000; */
			background-color: #000000;
			video{
				width: 100%;
				height: 100%;
			}
		}
		.info{
			width: 750rpx;
			height: 80rpx;
			background-color: #000000;
			text-align: center;
			text{
				margin: auto;
				color: #FFFFFF;
				font-size: 38rpx;
				line-height: 80rpx;
				font-weight: bold;
				letter-spacing: 50rpx
			}
		}
	}

	.main{
		.tvs{
			width: 650rpx;
			margin: 20px 50px 20rpx 50rpx;
			display: flex;
			//换行
			flex-wrap: wrap;
			//两边贴边对齐
			justify-content: space-between;
			.tv{
				width: 300rpx;
				height: 80rpx;
				margin-top: 20rpx;
				text-align: center;
				line-height: 80rpx;
				border-radius: 10rpx;
				background-color: #FFFFFF;
			}
			.active{
				width: 300rpx;
				height: 80rpx;
				margin-top: 20rpx;
				text-align: center;
				line-height: 80rpx;
				border-radius: 10rpx;
				border: 1px solid #ef5656;
				color: #ef5656;
			}
		}
		.falg{
			width: 750rpx;
			height: 50rpx;
			font-size: 25rpx;
			text-align: center;
			color: #ef5656;
		}
	}
	
</style>
