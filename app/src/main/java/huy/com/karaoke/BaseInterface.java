package huy.com.karaoke;

import huy.com.karaoke.Model.Example;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BaseInterface {
    @GET("search?order=date&part=snippet&channelId=UCL1DMUNMm6_G6LPblb9j3uQ&maxResults=25&key=AIzaSyDlsaihoE17YAUb-5cE0Gf348Lpm2Euy_s")
    Call<Example> getData();
}
