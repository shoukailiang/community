package com.shoukailiang.community.question.vo;

import com.shoukailiang.community.entities.Replay;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/18 15:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReplayVO extends Replay {

    List<Replay> children;
}
