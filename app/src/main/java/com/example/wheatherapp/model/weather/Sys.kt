import com.google.gson.annotations.SerializedName

data class Sys (

	@SerializedName("message") val message : Double,
	@SerializedName("country") val country : String,
	@SerializedName("sunrise") val sunrise : Int,
	@SerializedName("sunset") val sunset : Int
)