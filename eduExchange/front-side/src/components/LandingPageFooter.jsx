import React from "react";
import Link from "next/link";
import { Facebook, Instagram, Twitter, Linkedin } from "lucide-react";

const LandingPageFooter = () => {
  return (
    <div className="hidden sm:flex sm:flex-col sm:px-40">
      <div className="flex flex-1 justify-between items-center py-4">
        <div>edu.Exchange</div>
        <p className="text-light-primary text-sm font-light">
          © Copyright 2023 All Rights Reserved
        </p>
        <div className="flex gap-4 items-center">
          <Facebook />
          <Instagram />
          <Linkedin />
          <Twitter />
        </div>
      </div>
    </div>
  );
};

export default LandingPageFooter;
