package petrovich.ds.dto.deal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import petrovich.ds.dto.campaign.CampaignShortDTO;
import petrovich.ds.dto.coupon.CouponData;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CartProcessingResponse {
    private List<CampaignShortDTO> campaigns;
    private Set<String> availableCampaigns;
    private Set<String> appliedCampaigns;
    private DiscountData discountData;
    private PointsData pointsData;
    private SliderData sliderData;
    private List<ProductData> productsData;
    private CouponData promocodeData;
}
