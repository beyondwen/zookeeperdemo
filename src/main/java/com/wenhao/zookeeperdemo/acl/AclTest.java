package com.wenhao.zookeeperdemo.acl;

import com.wenhao.zookeeperdemo.zkoperating.BaseZookeeper;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

public class AclTest {

    public static void main(String[] args) throws Exception {
        BaseZookeeper baseZookeeper = new BaseZookeeper();
        baseZookeeper.connectZookeeper("11.0.0.110");
        Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin123"));
        ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
        Id id2 = new Id("digest", DigestAuthenticationProvider.generateDigest("guest:guest123"));
        ACL acl2 = new ACL(ZooDefs.Perms.ALL, id2);
        // 4.添加该账号
        ArrayList<ACL> aces = new ArrayList<>();
        aces.add(acl1);
        aces.add(acl2);
        baseZookeeper.createPersistentNode4ACL("/beyond1","wenhao",aces);
        //baseZookeeper.deleteNode("/wenhao11232131");
    }
}
